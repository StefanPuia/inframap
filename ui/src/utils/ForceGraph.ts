/**
 * Copyright 2021 Observable, Inc.
 * Released under the ISC license.
 * https://observablehq.com/@d3/force-directed-graph
 */
import * as d3 from 'd3';
import type {InfrastructureNode} from "../types";

type Link = { source: string, target: string };
type NodeInit = { nodes: InfrastructureNode[], links: Link[] };
type Options = { types: Record<string, string> }

export function ForceGraph({nodes, links}: NodeInit, {types}: Options) {
  const imageSize = 48;

  const width = window.innerWidth;
  const height = window.innerHeight;

  const svg = d3.create("svg")
    .attr("class", "absolute left-0 right-0 h-full z-10")
    .attr("viewBox", `0 0 ${width} ${height}`);

  const container = svg.append("g");


  const simulation = d3.forceSimulation()
    .nodes(nodes as any)
    .force("charge", d3.forceManyBody().strength(0))
    .force("link", d3.forceLink(links).id((d: any) => d.id).distance(imageSize * 4))
    .force("center", d3.forceCenter(width / 2, height / 2))
    .tick(1)
    .on("tick", ticked);


  const link = container.selectAll(".link")
    .data(links)
    .enter()
    .append("line")
    .attr("stroke", "#999");

  const node = container.selectAll(".node")
    .data(nodes)
    .enter()
    .append("g")
    .attr("class", "infrastructure-node cursor-pointer")
    .attr("data-id", d => d.id)
    .call(drag(simulation) as any);

  node.append("title").text(function (d) {
    return types[d.type] ? '' : `Type '${d.type}' not found`;
  });

  node
    .append("image")
    .attr("xlink:href", function (d) {
      return types[d.type] ?? "https://code.benco.io/icon-collection/azure-icons/Alerts.svg";
    })
    .attr("x", -imageSize / 2)
    .attr("y", -imageSize / 2)
    .attr("width", imageSize)
    .attr("height", imageSize);

  node.append("text")
    .attr("dx", imageSize / 2 + 6)
    .attr("dy", ".35em")
    // .attr("stroke", "white")
    .attr("fill", "white")
    .text(function (d) {
      return d.name
    });

  function ticked() {
    link.attr("x1", function (d: any) {
      return d.source.x;
    })
      .attr("y1", function (d: any) {
        return d.source.y;
      })
      .attr("x2", function (d: any) {
        return d.target.x;
      })
      .attr("y2", function (d: any) {
        return d.target.y;
      });

    node.attr("transform", function (d: any) {
      return "translate(" + d.x + "," + d.y + ")";
    });
  }

  function drag(simulation: any) {
    function dragstarted(event: any) {
      if (!event.active) simulation.alphaTarget(0.3).restart();
      event.subject.fx = event.subject.x;
      event.subject.fy = event.subject.y;
    }

    function dragged(event: any) {
      event.subject.fx = event.x;
      event.subject.fy = event.y;
    }

    function dragended(event: any) {
      if (!event.active) simulation.alphaTarget(0);
      event.subject.fx = null;
      event.subject.fy = null;
    }

    return d3.drag()
      .on("start", dragstarted)
      .on("drag", dragged)
      .on("end", dragended);
  }

  const zoom = d3.zoom().on('zoom', (e) => {
    container
      .attr('transform', e.transform);
  });
  svg.call(zoom as any);
  return svg.node();
}